class Question {
  String prompt;
  String reply;

  Question({required this.prompt, required this.reply});

  factory Question.fromJson(Map<String, dynamic> json) {
    return Question(
      prompt: json['question'],
      reply: json['reply'],
    );
  }
}

class QuestionList {
  List<Question> data;

  QuestionList({required this.data});

  factory QuestionList.fromJson(Map<String, dynamic> json) {
    List<dynamic> jsonData = json['data'];
    List<Question> dataList =
        jsonData.map((json) => Question.fromJson(json)).toList();
    return QuestionList(data: dataList);
  }
}
