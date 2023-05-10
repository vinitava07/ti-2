abstract class DashboardPresenter {
  Future<bool> checkHealth();
}

class DashboardPresenterImpl implements DashboardPresenter {
  @override
  Future<bool> checkHealth() {
    return Future.delayed(const Duration(seconds: 3), () => true);
  }
}
