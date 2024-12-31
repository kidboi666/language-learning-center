'use client';

import { useFormStatus } from 'react-dom';

export default function FormSubmit() {
  const status = useFormStatus();
  return (
    <button
      type="submit"
      disabled={status.pending}
      className="rounded-md bg-zinc-800 p-2 font-bold text-white"
    >
      {status.pending ? '로딩...' : '가입하기'}
    </button>
  );
}
