import 'package:http/http.dart' as http;

class HTTPClient {
  final String _path;
  final String _baseURL = "http://localhost:8080";

  HTTPClient(this._path);

  Future<http.Response> get() async {
    return await http.get(
      Uri.parse(_baseURL + _path),
    );
  }

  Future<http.Response> post() async {
    return await http.post(
      Uri.parse(_baseURL + _path),
      /**
       * final body = {
          'name': 'Bob',
          'age': '87',
        };
        final jsonString = json.encode(body);
        final uri = Uri.http('www.example.com', '/path');
        final headers = {HttpHeaders.contentTypeHeader: 'application/json'};
        final response = await http.post(uri, headers: headers, body: jsonString);
       */
    );
  }
}
