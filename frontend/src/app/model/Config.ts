export class Config {
  id: number;
  configName: string;
  configValue: string;

  constructor(id: number, configName: string, configValue: string) {
    this.id = id;
    this.configName = configName;
    this.configValue = configValue;
  }

}
