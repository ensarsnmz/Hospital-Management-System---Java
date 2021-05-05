public class Test extends ExaminationDecorator {
    Examination examination;

    public Test(Examination examination) {
        this.examination = examination;
    }

    @Override
    public int getCost() {
        return examination.getCost() + 7;
    }

    @Override
    public String getExaminations() {
        return examination.getExaminations() + " tests";
    }
}