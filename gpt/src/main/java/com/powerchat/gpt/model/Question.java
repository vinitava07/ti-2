class Question {

    private final UUID id;
    private final String question;

    private String reply;

    private final TimeStamp createdAt;

    private final UUID subscriptionId;

    Question(UUID id, String question, String reply, TimeStamp createdAt, UUID subscriptionId) {

        this.id = id;
        this.question = question;
        this.reply = reply;
        this.createdAt = createdAt;
        this.subscriptionId = subscriptionId;
    }

    Question(UUID id, String question, TimeStamp createdAt, UUID subscriptionId) {

        this.id = id;
        this.question = question;
        this.reply = "";
        this.createdAt = createdAt;
        this.subscriptionId = subscriptionId;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getReply() {
        return reply;
    }
    public void setReply(String reply) {
        this.reply = reply;
    }

    public TimeStamp getCreatedAt() {
        return createdAt;
    }

    public UUID getSubscriptionId() {
        return subscriptionId;
    }
}