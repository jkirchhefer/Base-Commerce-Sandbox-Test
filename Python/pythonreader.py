import json
import os
from basecommerce import *


def main():
    # path for the transactions json file
    # paths should be String
    transactions_json = "/home/justin/Documents/sandbox-test/transactions.json"

    # path for the transactions text file
    transactions_text = "/home/justin/Documents/sandbox-test/transactions.txt"

    # opens transaction json file and retrieves the transactions, storing them in a list
    with open(transactions_json, "r") as transactions_writer:
        transactions = transactions_writer.read()
        transactions = transactions.split("\n")

    # strips empty entries from the list
    transactions = list(filter(None, transactions))

    # converts json transactions to python objects
    for x in range(len(transactions)):
        transactions[x] = json.loads(transactions[x])

    # creates/initializes file for storing transaction IDs
    if not os.path.isfile(transactions_text):
        with open(transactions_text, "a+") as transactions_writer:
            transactions_writer.write("Format: Type,TransactionID\n")

    # authenticates client
    # credentials should be String
    client = BaseCommerceClient("0014480001", "YjSbhVjTp4zv3Jvw8F6g",
                                  "C88A85467391577A4A49A832DAF2D3E6D32F6D2092267540", True)

    # iterates through transactions, processing them and storing transaction types and IDs
    with open(transactions_text, "a") as transactions_writer:
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
