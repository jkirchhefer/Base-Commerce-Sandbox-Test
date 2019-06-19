import json
import os
from basecommerce import *


def main():
    # path for the transactions json file
    # paths should be String
    transactions_json = $path_to_transactions.json
    
    # path for the transactions text file
    transactions_text = $path_to_transactions.txt
    
    # opens transaction json file and retrieves the transactions, storing them in a list
    with open(transactions_json, "r") as f:
        transactions = f.read()
        transactions = transactions.split("\n")

    # strips empty entries from the list
    transactions = list(filter(None, transactions))

    # converts json transactions to python objects
    for x in range(len(transactions)):
        transactions[x] = json.loads(transactions[x])

    # creates/initializes file for storing transaction IDs
    if not os.path.isfile(transactions_text):
        with open(file, "a+") as f:
            f.write("Format: Type,TransactionID\n")

    # authenticates client
    # credentials should be String
    o_client = BaseCommerceClient($username, $password,
                                  $key, True)

    # iterates through transactions, processing them and storing transaction types and IDs
    with open(transactions_text, "a") as f:
        for transaction in transactions:
            if transaction["form"] == "BCT":
                bct = BankCardTransaction()
                bct.type = transaction["type"]
                bct.name = transaction["name"]
                bct.card_number = transaction["number"]
                bct.expiration_month = transaction["month"]
                bct.expiration_year = transaction["year"]
                bct.amount = transaction["amount"]
                bct = o_client.process_bank_card_transaction(bct)
                f.write("BCT," + str(bct.id) + "\n")

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
                bat = o_client.process_bank_account_transaction(bat)
                f.write("BAT," + str(bat.id) + "\n")


if __name__ == "__main__":
    main()
