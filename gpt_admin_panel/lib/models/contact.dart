class Contact {
  String name;
  String email;
  String phoneNumber;

  Contact({required this.name, required this.email, required this.phoneNumber});

  factory Contact.fromJson(Map<String, dynamic> json) {
    return Contact(
      name: json['name'],
      email: json['email'],
      phoneNumber: json['phoneNumber'],
    );
  }
}

class ContactList {
  List<Contact> data;

  ContactList({required this.data});

  factory ContactList.fromJson(Map<String, dynamic> json) {
    List<dynamic> jsonData = json['data'];
    List<Contact> dataList =
        jsonData.map((json) => Contact.fromJson(json)).toList();
    return ContactList(data: dataList);
  }
}
