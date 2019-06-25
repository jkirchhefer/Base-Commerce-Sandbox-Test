import json
import os
from basecommerce import *


def main():
    # path for the transactions json file
    # paths should be String
    transactions_json_path = $path_to_transactions.json

    # path for the transactions text file
    transactions_text_path = $path_to_transactions.txt

    # opens transaction json file and retrieves the transactions, storing them in a list
    with open(transactions_json_path, "r") as transactions_reader:
        transactions = transactions_reader.read()
        transactions = transactions.split("\n")

    # strips empty entries from the list
    transactions = list(filter(None, transactions))

    # converts json transactions to python objects
    for transactions_position in range(len(transactions)):
        transactions[transactions_position] = json.loads(transactions[transactions_position])

    # creates/initializes file for storing transaction IDs
    if not os.path.isfile(transactions_text_path):
        with open(transactions_text_path, "a+") as transactions_writer:
            transactions_writer.write("Format: Form,TransactionID\n")

    # authenticates client
    # credentials should be String
    client = BaseCommerceClient($username, $password, $key, True)

    # iterates through transactions, processing them and storing transaction forms and IDs
    with open(transactions_text_path, "a") as transactions_writer:
        for transaction in transactions:
            if transaction["form"] == "BCT":
                bct = BankCardTransaction()
                bct.type = transaction["type"]
                bct.name = transaction["name"]
                bct.card_number = transaction["number"]
                bct.expiration_month = transaction["month"]
                bct.expiration_year = transaction["year"]
                bct.amount = transaction["amount"]
                bct = client.process_bank_card_transaction(bct)
                transactions_writer.write("BCT," + str(bct.id) + "\n")

            elif transaction["form"] == "BAT":
                bat = BankAccountTransaction()
                bat.type = transaction["type"]
                bat.method = transaction["method"]
                bat.routing_number = transaction["rt_number"]
                bat.account_type = transaction["acct_type"]
                bat.account_name = transaction["name"]
                bat.account_number = transaction["acct_number"]
                bat.amount = transaction["amount"]
                bat.effective_date = datetime.now()
                bat = client.process_bank_account_transaction(bat)
                transactions_writer.write("BAT," + str(bat.id) + "\n")

    # prints session ID for later reference
    print("Session ID: " + client.session_id)


if __name__ == "__main__":
    main()
