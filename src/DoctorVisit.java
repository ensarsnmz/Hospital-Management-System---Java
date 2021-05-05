public class DoctorVisit extends ExaminationDecorator {
    Examination examination;

    public DoctorVisit(Examination examination) {
        this.examination = examination;
    }

    @Override
    public int getCost() {
        return examination.getCost() + 15;
    }

    @Override
    public String getExaminations() {
        return examination.getExaminations() + " doctorvisit";
    }
}