'use client';

import { PropsWithChildren, useState } from 'react';
import { Me, MeContext, Provider } from '@/context/use-me';

export default function ContextProvider({ children }: PropsWithChildren) {
  const [me, setMe] = useState<Me>({
    avatarUrl: '',
    createdAt: new Date(),
    email: '',
    id: 0,
    name: '',
    provider: Provider.LOCAL,
  });

  return (
    <MeContext.Provider value={{ me, setMe }}>{children}</MeContext.Provider>
  );
}
