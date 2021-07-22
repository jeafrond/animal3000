import { registerPlugin } from '@capacitor/core';

import type { AnimalPushNotificationPlugin } from './definitions';

const AnimalPushNotification = registerPlugin<AnimalPushNotificationPlugin>(
  'AnimalPushNotification',
  {
    web: () => import('./web').then(m => new m.AnimalPushNotificationWeb()),
  },
);

export * from './definitions';
export { AnimalPushNotification };
