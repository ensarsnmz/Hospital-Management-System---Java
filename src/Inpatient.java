public class Inpatient extends Examination{
    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getExaminations() {
        return "Inpatient" + "\t";
    }
}