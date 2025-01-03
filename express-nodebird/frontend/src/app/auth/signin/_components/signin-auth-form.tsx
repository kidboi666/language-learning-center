'use client';

import FormSubmitButton from '@/app/auth/_components/form-submit-button';
import OauthKakaoButton from '@/app/auth/_components/oauth-kakao-button';
import { ChangeEvent, FormEvent, useState } from 'react';
import Link from 'next/link';
import { signIn } from '@/app/actions/client/auth/signIn';

export default function SigninAuthForm() {
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  const handleChangeEmail = (e: ChangeEvent<HTMLInputElement>) =>
    setEmail(e.target.value);
  const handleChangePassword = (e: ChangeEvent<HTMLInputElement>) =>
    setPassword(e.target.value);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    await signIn(email, password);
  };

  return (
    <form
      onSubmit={(e) => handleSubmit(e)}
      className="flex w-96 flex-col items-center gap-4 rounded-lg bg-white p-4 shadow-lg"
    >
      <div className="relative flex w-full flex-1 justify-center">
        <Link
          href="/signup"
          className="absolute left-0 text-nowrap text-sm underline"
        >
          가입 하러 가기
        </Link>
        <h1 className="text-2xl font-bold text-zinc-800">로그인</h1>
      </div>
      <div className="flex w-full flex-col gap-2">
        <label htmlFor="email" className="text-zinc-800">
          이메일
        </label>
        <input
          id="email"
          name="email"
          type="email"
          value={email}
          onChange={handleChangeEmail}
          placeholder="이메일을 입력해주세요."
          className="w-full rounded-md bg-zinc-200 p-2 text-zinc-700 outline-0 ring-zinc-600 transition focus:ring-2"
        />
      </div>
      <div className="flex w-full flex-col gap-2">
        <label htmlFor="password" className="text-zinc-800">
          비밀번호
        </label>
        <input
          id="password"
          name="password"
          type="password"
          value={password}
          onChange={handleChangePassword}
          placeholder="비밀번호를 입력해주세요."
          className="w-full rounded-md bg-zinc-200 p-2 text-zinc-700 outline-0 ring-zinc-600 transition focus:ring-2"
        />
      </div>
      <FormSubmitButton>로그인 하기</FormSubmitButton>
      <OauthKakaoButton />
    </form>
  );
}
