This package contains the core files of SplitBill, i.e. the Texas Instruments TI-84 PLUS Silver
Edition graphing calculator and the printer. I figured I'd put things here to (try to) reserve the
main SplitBill package to Fragments, Activities, ViewModels and RecyclerView adapters.

If you're here from the Printer, basically the Android system defines the Internal Storage as the
app's working directory that is inaccessible to everybody and everything except the app itself, and
the root user. More specifically, the Internal Storage (in our case) is defined as
"/data/data/com.whatamidoingwithmylife.splitbill/".

The external storage is the storage accessible to everyone, typically where the user keeps the rest
of their stuff too. On my Galaxy S5 running LineageOS 15.1 based on Android 8.1 Oreo, this directory
is defined as "/storage/emulated/0". I know that OEMs such as Samsung make theirs all weird, but the
Android system knows what it is so I don't have to. Everybody's happy at the end of the day.

I could also write to an SD card inserted into the device, but it's 2018 and apparently not all
Android devices have SD card slots, so... yeah.