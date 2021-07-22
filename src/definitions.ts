export interface AnimalPushNotificationPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
