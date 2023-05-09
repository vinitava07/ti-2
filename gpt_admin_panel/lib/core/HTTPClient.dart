import 'package:http/http.dart' as http;

class HTTPClient {
  final String _path;
  final String _baseURL = "http://localhost:8080";

  HTTPClient(this._path);

  Future<http.Response> get() async {
    return await http.get(
      Uri.parse(_path),
    );
  }
}
