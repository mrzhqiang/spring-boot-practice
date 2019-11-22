package chapter03.section04.example01;

public class LinuxListService implements ListService {
  @Override public String showListCmd() {
    return "ls";
  }
}
