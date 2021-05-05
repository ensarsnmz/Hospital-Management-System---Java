public class Measurement extends ExaminationDecorator {
    Examination examination;

    public Measurement(Examination examination) {
        this.examination = examination;
    }

    @Override
    public int getCost() {
        return examination.getCost() + 5;
    }

    @Override
    public String getExaminations() {
        return examination.getExaminations() + " measurement";
    }
}