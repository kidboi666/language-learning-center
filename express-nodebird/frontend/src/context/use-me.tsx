'use client';

import { createContext, Dispatch, SetStateAction, useContext } from 'react';

export enum Provider {
  LOCAL = 'local',
  KAKAO = 'kakao',
}

export interface Me {
  id: number;
  name: string | null;
  email: string;
  provider: Provider;
  avatarUrl: string | null;
  createdAt: Date;
}

interface UseMeContext {
  me: Me;
  setMe: Dispatch<SetStateAction<Me>>;
}

export const MeContext = createContext<UseMeContext>({
  me: {
    id: 0,
    name: '',
    email: '',
    provider: Provider.LOCAL,
    avatarUrl: '',
    createdAt: new Date(),
  },
  setMe: () => {},
});

export default function useMe() {
  const { me, setMe } = useContext(MeContext);
  return { me, setMe };
}
