package org.ntutssl.termfrequency;

public class WordFrequencyController {
    private IOHandler handler;
    private IStopWordManager swm;
    private IDataStorageManager dsm;
    private IWordFrequencyManager wfm;

    public WordFrequencyController(IOHandler handler, IStopWordManager swm, IDataStorageManager dsm,
            IWordFrequencyManager wfm) {
        this.handler = handler;
        this.swm = swm;
        this.dsm = dsm;
        this.wfm = wfm;
    }

    public void run(String order, Integer range, String outputPath) {
        
        for (String word : this.dsm.getWords()) {
            if (!this.swm.isStopWordSet(word)) {
                this.wfm.incrementCount(word);
            }
        }
        // System.out.println(dsm.getWords().size());
        if (range > dsm.getWords().size() || range < 1)
            throw new WordFrequencyException(String.format("Out of range! The range should be from 1 to %d.", dsm.getWords().size()));
        this.wfm.output(outputPath, order, range, handler);

    }
}