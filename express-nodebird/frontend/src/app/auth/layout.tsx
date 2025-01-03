import { PropsWithChildren } from 'react';

export default function Layout({ children }: PropsWithChildren) {
  return (
    <div className="flex h-dvh items-center justify-center bg-zinc-100 pt-20">
      {children}
    </div>
  );
}
