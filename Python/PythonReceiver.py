from basecommerce import *
import os


def main():
    # path for the statuses file
    file = "/home/justin/Documents/sandbox-test/statuses.csv"

    # opens the transactions file for reading and stores the transaction IDs in a list
    with open("/home/justin/Documents/sandbox-test/transactions.txt", "r") as f:
        f.readline()
        ids = f.read()
        ids = ids.split("\n")

    # removes blank entries
    ids = list(filter(None, ids))

    # filters transaction type and id
    for x in range(len(ids)):
        ids[x] = ids[x].split(",")
        ids[x][1] = int(ids[x][1])

    # creates/initializes statuses file if DNE
    if not os.path.isfile(file):
        with open(file, "a") as f:
            f.write("Name,ID,Amount,Status\n")

    # authenticates client
    o_client = BaseCommerceClient("0014480001", "YjSbhVjTp4zv3Jvw8F6g",
                                  "C88A85467391577A4A49A832DAF2D3E6D32F6D2092267540", True)

    # gets the transaction statuses depending on type and id and stores them in the statuses file
    with open("/home/justin/Documents/sandbox-test/statuses.csv", "a") as f:
        for x in ids:
            if x[0] == "BCT":
                transaction = o_client.get_bank_card_transaction(x[1])
                name = transaction.name
            elif x[0] == "BAT":
                transaction = o_client.get_bank_account_transaction(x[1])
                name = transaction.account_name
            amount = transaction.amount
            status = transaction.status
            f.write(name + "," + str(x[1]) + "," + str(amount) + "," + status + "\n")


if __name__ == "__main__":
    main()
