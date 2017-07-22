/**
 * Created by dcornea on 6/9/17.
 */
public class ReportCard {

    private final String math = "math";
    private final String romanian = "romanian";
    private final String hungarian = "hungarian";
    private final String physics = "physics";
    private final String history = "history";
    private final String geography = "geography";
    private int mathMark;
    private int romanianMark;
    private int hungarianMark;
    private int physicsMark;
    private int historyMark;
    private int geographyMark;


    public ReportCard(int mathMark, int romanianMark, int hungarianMark, int physicsMark, int historyMark, int geographyMark) {
        this.mathMark = mathMark;
        this.romanianMark = romanianMark;
        this.hungarianMark = hungarianMark;
        this.physicsMark = physicsMark;
        this.historyMark = historyMark;
        this.geographyMark = geographyMark;
    }

    public String getMath() {
        return math;
    }

    public String getRomanian() {
        return romanian;
    }

    public String getHungarian() {
        return hungarian;
    }

    public String getPhysics() {
        return physics;
    }

    public String getHistory() {
        return history;
    }

    public String getGeography() {
        return geography;
    }

    public int getMathMark() {
        return mathMark;
    }

    public void setMathMark(int mathMark) {
        this.mathMark = mathMark;
    }

    public int getRomanianMark() {
        return romanianMark;
    }

    public void setRomanianMark(int romanianMark) {
        this.romanianMark = romanianMark;
    }

    public int getHungarianMark() {
        return hungarianMark;
    }

    public void setHungarianMark(int hungarianMark) {
        this.hungarianMark = hungarianMark;
    }

    public int getPhysicsMark() {
        return physicsMark;
    }

    public void setPhysicsMark(int physicsMark) {
        this.physicsMark = physicsMark;
    }

    public int getHistoryMark() {
        return historyMark;
    }

    public void setHistoryMark(int historyMark) {
        this.historyMark = historyMark;
    }

    public int getGeographyMark() {
        return geographyMark;
    }

    public void setGeographyMark(int geographyMark) {
        this.geographyMark = geographyMark;
    }

    @Override
    public String toString() {
        return "ReportCard\n" +
                "math = " + mathMark +
                "\nromanian = " + romanianMark +
                "\nhungarian = " + hungarianMark +
                "\nphysics = " + physicsMark +
                "\nhistory = " + historyMark +
                "\ngeography = " + geographyMark;
    }
}
