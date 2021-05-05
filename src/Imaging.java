public class Imaging extends ExaminationDecorator{
    Examination examination;

    public Imaging(Examination examination) {
        this.examination = examination;
    }

    @Override
    public int getCost() {
        return examination.getCost() + 10;
    }

    @Override
    public String getExaminations() {
        return examination.getExaminations() + " imaging";
    }
}