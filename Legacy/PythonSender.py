from basecommerce import *
import os


def main():
    # creates/initializes the transactions file
    # path should be String
    file = $path_to_transactions.txt
    if not os.path.isfile(file):
        with open(file, "a+") as f:
            f.write("Format: Type,TransactionID \n")

    # creates/initializes the transaction
    # should pass
    transaction1 = BankCardTransaction()
    transaction1.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction1.name = "BCT 1"
    transaction1.card_number = "4111111111111111"
    transaction1.expiration_month = "10"
    transaction1.expiration_year = "2019"
    transaction1.amount = 5.25

    # ACH transaction
    ACH1 = BankAccountTransaction()
    ACH1.type = BankAccountTransaction.XS_BAT_TYPE_DEBIT
    ACH1.method = BankAccountTransaction.XS_BAT_METHOD_CCD
    ACH1.routing_number = "021000021"
    ACH1.account_type = BankAccountTransaction.XS_BAT_ACCOUNT_TYPE_CHECKING
    ACH1.account_name = "BAT 1"
    ACH1.account_number = "12345"
    ACH1.amount = 10.18
    ACH1.effective_date = datetime.now()

    # should fail
    transaction2 = BankCardTransaction()
    transaction2.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction2.name = "BAT 2"
    transaction2.card_number = "4111111111111111"
    transaction2.expiration_month = "10"
    transaction2.expiration_year = "2019"
    transaction2.amount = 5.30

    # random
    transaction3 = BankCardTransaction()
    transaction3.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction3.name = "Transaction 3"
    transaction3.card_number = "371449635398413"
    transaction3.expiration_month = "03"
    transaction3.expiration_year = "2020"
    transaction3.amount = 2.44

    # random
    transaction4 = BankCardTransaction()
    transaction4.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction4.name = "Transaction 4"
    transaction4.card_number = "4222222222222222"
    transaction4.expiration_month = "12"
    transaction4.expiration_year = "2022"
    transaction4.amount = 3.36

    # random
    transaction5 = BankCardTransaction()
    transaction5.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction5.name = "Transaction 5"
    transaction5.card_number = "6011000990139424"
    transaction5.expiration_month = "08"
    transaction5.expiration_year = "1337"
    transaction5.amount = 2.89

    # random
    transaction6 = BankCardTransaction()
    transaction6.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction6.name = "Transaction 6"
    transaction6.card_number = "378282246310005"
    transaction6.expiration_month = "05"
    transaction6.expiration_year = "2023"
    transaction6.amount = 6.21

    # random
    transaction7 = BankCardTransaction()
    transaction7.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction7.name = "Transaction 6"
    transaction7.card_number = "5555555555554444"
    transaction7.expiration_month = "11"
    transaction7.expiration_year = "2020"
    transaction7.amount = 5.34

    # random
    transaction8 = BankCardTransaction()
    transaction8.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction8.name = "Transaction 7"
    transaction8.card_number = "4222222222222222"
    transaction8.expiration_month = "02"
    transaction8.expiration_year = "2020"
    transaction8.amount = 3.35

    # random
    transaction9 = BankCardTransaction()
    transaction9.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction9.name = "Transaction 8"
    transaction9.card_number = "1111555599997777"
    transaction9.expiration_month = "ten"
    transaction9.expiration_year = "2019"
    transaction9.amount = 5.22

    # random
    transaction10 = BankCardTransaction()
    transaction10.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction10.name = "Transaction 9"
    transaction10.card_number = "30569309025904"
    transaction10.expiration_month = "5"
    transaction10.expiration_year = "2020"
    transaction10.amount = 5.25

    # random
    transaction11 = BankCardTransaction()
    transaction11.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction11.name = "Transaction 10"
    transaction11.card_number = "371449635398431"
    transaction11.expiration_month = "12"
    transaction11.expiration_year = "2019"
    transaction11.amount = 0.00

    # random
    transaction12 = BankCardTransaction()
    transaction12.type = BankCardTransaction.XS_BCT_TYPE_SALE
    transaction12.name = "Transaction 11"
    transaction12.card_number = "4111111111111111"
    transaction12.expiration_month = "13"
    transaction12.expiration_year = "2037"
    transaction12.amount = 4.85

    # authenticates the client and processes the transaction
    # credentials should be String
    o_client = BaseCommerceClient($username, $password,
                                  $key, True)
    transaction1 = o_client.process_bank_card_transaction(transaction1)
    transaction2 = o_client.process_bank_card_transaction(transaction2)
    transaction3 = o_client.process_bank_card_transaction(transaction3)
    transaction4 = o_client.process_bank_card_transaction(transaction4)
    transaction5 = o_client.process_bank_card_transaction(transaction5)
    transaction6 = o_client.process_bank_card_transaction(transaction6)
    transaction7 = o_client.process_bank_card_transaction(transaction7)
    transaction8 = o_client.process_bank_card_transaction(transaction8)
    transaction9 = o_client.process_bank_card_transaction(transaction9)
    transaction10 = o_client.process_bank_card_transaction(transaction10)
    transaction11 = o_client.process_bank_card_transaction(transaction11)
    transaction12 = o_client.process_bank_card_transaction(transaction12)

    # process ACH transaction
    ACH1 = o_client.process_bank_account_transaction(ACH1)

    # creates/initializes list of transactions
    transactions = []
    transactions.append(transaction1)

    # test adding ACH transaction
    transactions.append(ACH1)

    transactions.append(transaction2)
    transactions.append(transaction3)
    transactions.append(transaction4)
    transactions.append(transaction5)
    transactions.append(transaction6)
    transactions.append(transaction7)
    transactions.append(transaction8)
    transactions.append(transaction9)
    transactions.append(transaction10)
    transactions.append(transaction11)
    transactions.append(transaction12)

    # writes the transaction IDs to the transactions file
    # make it process the transaction in the loop
    with open(file, "a+") as f:
        for transaction in transactions:
            if str(type(transaction)) == "<class 'basecommerce.bank_card_transaction.BankCardTransaction'>":
                f.write("BCT," + str(transaction.id) + "\n")
            elif str(type(transaction)) == "<class 'basecommerce.bank_account_transaction.BankAccountTransaction'>":
                f.write("BAT," + str(transaction.id) + "\n")
                
    # prints session ID for later reference
    print("Session ID: " + o_client.session_id)


if __name__ == "__main__":
    main()
