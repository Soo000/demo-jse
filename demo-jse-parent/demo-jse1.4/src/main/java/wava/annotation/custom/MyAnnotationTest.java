package wava.annotation.custom;

@MyAnnotation(author = "wangke", 
			  currentVersion = 2.0,
			  lastModified = "20180322",
			  reviews = {"wangke", "soosky"},
			  lastModifiedBy = "wangke",
			  value = "测试自定义注解",
			  date = "20180322")
public class MyAnnotationTest {

	private @NotNull String name;
	
	public MyAnnotationTest() {
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
