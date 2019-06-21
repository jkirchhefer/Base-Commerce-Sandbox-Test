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

    # filters transaction type and id
    for transactions_position in range(len(transactions)):
        transactions[transactions_position] = transactions[transactions_position].split(",")
        transactions[transactions_position][1] = int(transactions[transactions_position][1])

    # creates/initializes statuses file if DNE
    if not os.path.isfile(statuses_path):
        with open(statuses_path, "a") as transactions_writer:
            transactions_writer.write("Name,ID,Amount,Status\n")

    # authenticates client
    # credentials should be String
    client = BaseCommerceClient($username, $password, $key, True)

    # gets the transaction statuses depending on type and id and stores them in the statuses file
    with open(statuses_file_path, "a") as transactions_writer:
        for transaction in transactions:
            if transaction[0] == "BCT":
                transactions_writer = client.get_bank_card_transaction(transaction[1])
                name = transactions_writer.name
            elif transaction[0] == "BAT":
                transactions_writer = client.get_bank_account_transaction(transaction[1])
                name = transactions_writer.account_name
            amount = transactions_writer.amount
            status = transactions_writer.status
            transactions_writer.write(name + "," + str(transaction[1]) + "," + str(amount) + "," + status + "\n")

    # prints session ID for later reference
    print("Session ID: " + client.session_id)


if __name__ == "__main__":
    main()
