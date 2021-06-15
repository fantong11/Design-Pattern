package org.ntutssl.termfrequency;

public class Main {
    public static void main(String[] args) {
        IOHandler handler = new IOHandler();
        IStopWordManager swm = new StopWordManager(args[0], handler);
        IDataStorageManager dsm = new DataStorageManager(args[1], handler);
        IWordFrequencyManager wfm = new WordFrequencyManager();
        WordFrequencyController wfc = new WordFrequencyController(handler, swm, dsm, wfm);

        wfc.run(args[4], Integer.valueOf(args[3]), args[2]);
    }
}