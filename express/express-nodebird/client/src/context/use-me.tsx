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
  avatar_url: string | null;
  created_at: Date;
  sns_id: number | null;
}

interface UseMeContext {
  me: Me;
  setMe: Dispatch<SetStateAction<Me>>;
}

export const MeContext = createContext<UseMeContext>({
  me: {
    id: 0,
    name: null,
    email: '',
    provider: Provider.LOCAL,
    avatar_url: null,
    created_at: new Date(),
    sns_id: null,
  },
  setMe: () => {},
});

export default function useMe() {
  const { me, setMe } = useContext(MeContext);
  return { me, setMe };
}
