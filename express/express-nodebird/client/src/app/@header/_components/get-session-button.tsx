'use client';

import Button from '@/components/Button';
import { getSession } from '@/app/actions/client/auth/get-session';

export default function GetSessionButton() {
  const handleSession = async () => {
    const res = await getSession();
    console.log(res);
  };
  return <Button onClick={handleSession}>세션 확인하기</Button>;
}
