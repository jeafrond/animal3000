export interface AnimalPushNotificationPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  showNotification(options: { 
    urlImg: string,
    Color: string,
    Text: string,
    urlImagePartenaire: string
   }): Promise<{ value: string }>;
}
