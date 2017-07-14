package cn.quyf.demo.json;

public class Theme {

	private Integer themeId;
	private String themeName;
	
	public Theme() {
		// TODO Auto-generated constructor stub
	}

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	@Override
	public String toString() {
		return "Theme [themeId=" + themeId + ", themeName=" + themeName + "]";
	}
	
}
