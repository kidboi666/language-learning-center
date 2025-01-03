'use client';

import FormSubmitButton from '@/app/(auth)/_components/form-submit-button';
import OauthKakaoButton from '@/app/(auth)/_components/oauth-kakao-button';
import { useActionState } from 'react';
import { signUpAction } from '@/app/actions/server/auth/auth-action';

export default function SignupAuthForm() {
  const [state, action, pending] = useActionState(signUpAction, null);
  console.log(state);
  return (
    <form
      action={action}
      className="flex w-96 flex-col items-center gap-4 rounded-lg bg-white p-4 shadow-lg"
    >
      <h1 className="text-2xl font-bold text-zinc-800">회원가입</h1>
      <div className="flex w-full flex-col gap-2">
        <label htmlFor="email" className="text-zinc-800">
          이메일
        </label>
        <input
          id="email"
          name="email"
          type="email"
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
          placeholder="비밀번호를 입력해주세요."
          className="w-full rounded-md bg-zinc-200 p-2 text-zinc-700 outline-0 ring-zinc-600 transition focus:ring-2"
        />
      </div>
      <FormSubmitButton isPending={pending}>가입 하기</FormSubmitButton>
      <OauthKakaoButton />
    </form>
  );
}
