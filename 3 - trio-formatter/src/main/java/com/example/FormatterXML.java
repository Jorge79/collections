package com.example;

import java.util.List;
import java.util.Map;

public class FormatterXML extends DataProcessor {

	@Override
	public String format(Map<String, Object> data) {
		if (data == null || data.isEmpty()) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root/>";
		}

		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<root>\n");

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value instanceof List) {
				xml.append("  <").append(key).append(">\n");
				for (Object item : (List<?>) value) {
					xml.append("    <item>").append(ConvertXML(item.toString())).append("</item>\n");
				}
				xml.append("  </").append(key).append(">\n");
			} else {
				xml.append("  <").append(key).append(">")
						.append(ConvertXML(value.toString()))
						.append("</").append(key).append(">\n");
			}
		}

		xml.append("</root>");
		return xml.toString();
	}

	private String ConvertXML(String text) {
		return text.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\"", "&quot;")
				.replace("'", "&apos;");
	}
}