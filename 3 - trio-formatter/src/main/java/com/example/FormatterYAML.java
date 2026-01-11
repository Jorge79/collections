package com.example;

import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class FormatterYAML extends DataProcessor {

	private final Yaml yaml;

	public FormatterYAML() {
		super();
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);
		options.setIndent(2);
		this.yaml = new Yaml(options);
	}

	@Override
	public String format(Map<String, Object> data) {
		return yaml.dump(data);
	}
}