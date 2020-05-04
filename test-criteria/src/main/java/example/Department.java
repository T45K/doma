package example;

import java.util.ArrayList;
import java.util.List;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.Transient;
import org.seasar.doma.Version;

@Entity
public class Department {

  @Id Integer departmentId;
  Integer departmentNo;
  String departmentName;
  String location;
  @Version Integer version;
  @OriginalStates Department originalStates;
  @Transient List<Employee> employeeList = new ArrayList<>();

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public Integer getDepartmentNo() {
    return departmentNo;
  }

  public void setDepartmentNo(Integer departmentNo) {
    this.departmentNo = departmentNo;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Department getOriginalStates() {
    return originalStates;
  }

  public void setOriginalStates(Department originalStates) {
    this.originalStates = originalStates;
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public void setEmployeeList(List<Employee> employeeList) {
    this.employeeList = employeeList;
  }
}