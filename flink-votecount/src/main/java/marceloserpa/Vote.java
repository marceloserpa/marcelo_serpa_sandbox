package marceloserpa;

public class Vote {

    private String userId;
    private String candidateId;

    public Vote() {
    }

    public Vote(String userId, String candidateId) {
        this.userId = userId;
        this.candidateId = candidateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }
}
