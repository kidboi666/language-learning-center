'use client';

import { PropsWithChildren, useState } from 'react';
import { Me, MeContext } from '@/context/use-me';

export default function ContextProvider({ children }: PropsWithChildren) {
  const [me, setMe] = useState<Me | null>(null);

  return (
    <MeContext.Provider value={{ me, setMe }}>{children}</MeContext.Provider>
  );
}
