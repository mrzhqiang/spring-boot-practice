package chapter03.section04.example01;

public class WindowsListService implements ListService {
  @Override public String showListCmd() {
    return "dir";
  }
}
