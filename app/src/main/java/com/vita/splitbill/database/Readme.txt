This package has the important database bits: the database itself, and its DAOs.

The database is stored in
"/data/data/com.whatamidoingwithmylife.splitbill/databases/splitbilldatabase.db".
Good luck getting there on an unrooted device, though.

If you plan on printing the entire "SplitBillRoomDatabase.java" file, I strongly
recommend against that. Thousands of those lines are database entries. Rather, print
everything other than the PopulateDbAsync inner class.
