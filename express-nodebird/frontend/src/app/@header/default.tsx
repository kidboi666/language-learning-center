'use client';

import GetSessionButton from '@/app/@header/_components/get-session-button';
import { useEffect, useState } from 'react';
import LoginButton from '@/app/@header/_components/login-button';
import LogoutButton from '@/app/@header/_components/logout-button';
import { getSession } from '@/app/actions/client/auth/get-session';

export default function Default() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleFetch = async () => {
    const res = await getSession();

    if (res.status === 'success') {
      setIsLoggedIn(res.data.isLoggedIn);
    }
  };

  useEffect(() => {
    void handleFetch();
  }, []);

  return (
    <div className="fixed left-0 top-0 flex w-full gap-2 bg-white p-2 shadow-md">
      {isLoggedIn ? <LogoutButton /> : <LoginButton />}
      <GetSessionButton />
    </div>
  );
}
