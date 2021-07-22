import { WebPlugin } from '@capacitor/core';

import type { AnimalPushNotificationPlugin } from './definitions';

export class AnimalPushNotificationWeb
  extends WebPlugin
  implements AnimalPushNotificationPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
