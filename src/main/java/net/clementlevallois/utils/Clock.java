/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

/**
 *
 * @author C. Levallois
 */
public class Clock {

    private long start;
    private final String action;
    private StringBuilder logText;
    private StringBuilder intermediateText;
    private final String newLine = "\n";
    private final String interval = "-------------------------------\n\n";

    /**
     *
     */
    public boolean silent = false;

    /**
     *
     * @param action
     */
    public Clock(String action) {

        this.action = action;
        intermediateText = new StringBuilder();
        logText = new StringBuilder();
        if (!silent) {
            startClock(false);
        }
    }

    /**
     *
     * @param action
     * @param startSilent
     */
    public Clock(String action, boolean startSilent) {
        this.action = action;
        intermediateText = new StringBuilder();
        logText = new StringBuilder();
        startClock(startSilent);
    }

    /**
     *
     * @param action
     * @param useTheOutputEnum
     */
    public Clock(String action, Output useTheOutputEnum) {
        this.action = action;
        intermediateText = new StringBuilder();
        logText = new StringBuilder();
    }

    private void startClock(boolean startSilent) {

        start = System.currentTimeMillis();
        logText.append(action).append("...").append(newLine);
        if (!startSilent) {
            System.out.print(logText.toString());
        }
    }

    /**
     *
     * @return
     */
    public String startClockToString() {
        start = System.currentTimeMillis();
        logText.append(action).append("...").append(newLine);
        return logText.toString();
    }

    /**
     *
     * @param it
     */
    public void printIntermediaryText(String it) {
        intermediateText.append(it).append(newLine);
        System.out.println(intermediateText.toString());
        intermediateText = new StringBuilder();
    }

    /**
     *
     * @param it
     * @return
     */
    public String printIntermediaryTextToString(String it) {
        intermediateText = new StringBuilder();
        intermediateText.append(it).append(newLine);
        return intermediateText.toString();
    }

    /**
     *
     * @return
     */
    public long getElapsedTime() {
        long currentTime = System.currentTimeMillis();
        return currentTime - start;
    }

    /**
     *
     */
    public void printElapsedTime() {
        System.out.println(computeElapsedTime());
    }

    /**
     *
     * @return
     */
    public String printElapsedTimeTostring() {
        return computeElapsedTime();
    }

    private String computeElapsedTime() {
        StringBuilder sb = new StringBuilder();
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - start;

        if (elapsedTime < 10000) {
            int seconds = Math.round(elapsedTime / 1000);
            if (seconds != 0) {
                sb.append(String.valueOf(seconds))
                        .append(" seconds, ");
            }
            sb.append(Math.round(elapsedTime % 1000))
                    .append(" milliseconds");
        } else if (elapsedTime < 60000) {
            sb.append(elapsedTime / 1000).append(" seconds");
        } else {
            sb.append(elapsedTime / 60000).append(" minutes ").append(Math.round((elapsedTime % 60000) / 1000)).append(" seconds");
        }
        return sb.toString();
    }

    /**
     *
     */
    public void closeAndPrintClock() {
        System.out.println(writeLogTextClosing(""));
    }

    /**
     *
     * @param closingMessage
     */
    public void closeAndPrintClock(String closingMessage) {
        System.out.println(writeLogTextClosing(closingMessage));
    }

    /**
     *
     * @param closingMessage
     * @return
     */
    public String closeAndPrintClockToString(String closingMessage) {
        return writeLogTextClosing(closingMessage);
    }

    private String writeLogTextClosing(String closingMessage) {
        if (!silent) {
            logText = new StringBuilder();
            logText.append(closingMessage)
            .append(newLine)
            .append("finished ")
            .append(action)
            .append(". [Duration: ")
            .append(computeElapsedTime())
            .append("]");
        }
        return logText.toString();
    }

    /**
     *
     * @return
     */
    public String getAction() {
        return action;
    }
}
