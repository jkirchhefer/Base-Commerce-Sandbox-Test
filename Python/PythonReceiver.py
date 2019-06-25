from basecommerce import *
import os


def main():
    # path for the statuses file
    # paths should be String
    statuses_file_path = $path_to_statuses.csv

    # path for the transactions file
    transactions_file_path = $path_to_transactions.txt

    # opens the transactions file for reading and stores the transaction IDs in a list
    with open(transactions_file_path, "r") as transactions_reader:
        transactions_reader.readline()
        transactions = transactions_reader.read()
        transactions = transactions.split("\n")

    # removes blank entries
    transactions = list(filter(None, transactions))

    # filters transaction form and ID
    for transactions_position in range(len(transactions)):
        transactions[transactions_position] = transactions[transactions_position].split(",")
        transactions[transactions_position][1] = int(transactions[transactions_position][1])

    # creates/initializes statuses file if DNE
    if not os.path.isfile(statuses_file_path):
        with open(statuses_file_path, "a") as status_writer:
            status_writer.write("Name,ID,Amount,Status\n")

    # authenticates client
    # credentials should be String
    client = BaseCommerceClient($username, $password, $key, True)

    # gets the transaction statuses depending on form and ID and stores them in the statuses file
    with open(statuses_file_path, "a") as status_writer:
        for transaction_info in transactions:
            transaction_id = transaction_info[1]
            transaction_form = transaction_info[0]
            if transaction_form == "BCT":
                transaction = client.get_bank_card_transaction(transaction_id)
                name = transaction.name
            elif transaction_form == "BAT":
                transaction = client.get_bank_account_transaction(transaction_id)
                name = transaction.account_name
            amount = transaction.amount
            status = transaction.status
            status_writer.write(name + "," + str(transaction_id) + "," + str(amount) + "," + status + "\n")

    # prints session ID for later reference
    print("Session ID: " + client.session_id)


if __name__ == "__main__":
    main()
