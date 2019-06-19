import json


def main():
    # creates/initializes transactions
    # should pass
    bct1 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 1",
        "number": "4111111111111111",
        "month": "10",
        "year": "2020",
        "amount": 5.25,
    }

    # should pass
    bat1 = {
        "form": "BAT",
        "type": "DEBIT",
        "method": "CCD",
        "rt_number": "021000021",
        "acct_type": "CHECKING",
        "name": "BAT 1",
        "acct_number": "12345",
        "amount": 10.18,
        "date": "now"
    }

    # should fail
    bct2 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 2",
        "number": "4111111111111111",
        "month": "10",
        "year": "2019",
        "amount": 5.30,
    }

    # random
    bct3 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 3",
        "number": "371449635398413",
        "month": "03",
        "year": "2020",
        "amount": 2.44,
    }

    # should fail
    bat2 = {
        "form": "BAT",
        "type": "DEBIT",
        "method": "CCD",
        "rt_number": "123456789",
        "acct_type": "CHECKING",
        "name": "BAT 2",
        "acct_number": "55555",
        "amount": 6.40,
        "date": "now"
    }

    # random
    bat3 = {
        "form": "BAT",
        "type": "DEBIT",
        "method": "CCD",
        "rt_number": "313376669",
        "acct_type": "CHECKING",
        "name": "BAT 3",
        "acct_number": "22145",
        "amount": 2.99,
        "date": "now"
    }

    # random
    bat4 = {
        "form": "BAT",
        "type": "DEBIT",
        "method": "CCD",
        "rt_number": "889634299",
        "acct_type": "CHECKING",
        "name": "BAT 4",
        "acct_number": "886627",
        "amount": 4.33,
        "date": "now"
    }

    # random
    bct4 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 4",
        "number": "4222222222222222",
        "month": "12",
        "year": "2022",
        "amount": 3.36,
    }

    # random
    bct5 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 5",
        "number": "6011000990139424",
        "month": "08",
        "year": "1337",
        "amount": 2.89,
    }

    bct6 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 6",
        "number": "378282246310005",
        "month": "05",
        "year": "2023",
        "amount": 6.21,
    }

    bat5 = {
        "form": "BAT",
        "type": "DEBIT",
        "method": "CCD",
        "rt_number": "889364524",
        "acct_type": "CHECKING",
        "name": "BAT 5",
        "acct_number": "21586",
        "amount": 8.91,
        "date": "now"
    }

    # random
    bct7 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 7",
        "number": "5555555555554444",
        "month": "11",
        "year": "2020",
        "amount": 5.34,
    }

    # random
    bct8 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 8",
        "number": "4222222222222222",
        "month": "02",
        "year": "2020",
        "amount": 3.35,
    }

    # random
    bct9 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 9",
        "number": "1111555599997777",
        "month": "ten",
        "year": "2019",
        "amount": 5.22,
    }

    # random
    bct10 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 10",
        "number": "30569309025904",
        "month": "9",
        "year": "2020",
        "amount": 5.25,
    }

    # random
    bct11 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 11",
        "number": "371449635398431",
        "month": "12",
        "year": "2019",
        "amount": 0.00,
    }

    # random
    bct12 = {
        "form": "BCT",
        "type": "SALE",
        "name": "BCT 12",
        "number": "4111111111111111",
        "month": "13",
        "year": "2037",
        "amount": 4.85,
    }
    
    # path for the transactions json file
    transactions_json = $path_to_transactions.json

    # creates a list of transactions to be stored
    transactions = []
    transactions.append(json.dumps(bct1))
    transactions.append(json.dumps(bat1))
    transactions.append(json.dumps(bat2))
    transactions.append(json.dumps(bct2))
    transactions.append(json.dumps(bct3))
    transactions.append(json.dumps(bat3))
    transactions.append(json.dumps(bat4))
    transactions.append(json.dumps(bct4))
    transactions.append(json.dumps(bct5))
    transactions.append(json.dumps(bct6))
    transactions.append(json.dumps(bat5))
    transactions.append(json.dumps(bct7))
    transactions.append(json.dumps(bct8))
    transactions.append(json.dumps(bct9))
    transactions.append(json.dumps(bct10))
    transactions.append(json.dumps(bct11))
    transactions.append(json.dumps(bct12))

    # iterates through transactions, writing them to a file
    with open(transacions_json, "a") as f:
        for transaction in transactions:
            f.write(transaction + "\n")


if __name__ == "__main__":
    main()
