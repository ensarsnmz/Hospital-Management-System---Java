public class Outpatient extends Examination {
    @Override
    public int getCost() {
        return 15;
    }

    @Override
    public String getExaminations() {
        return "Outpatient" + "\t";
    }
}