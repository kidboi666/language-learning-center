import { ComponentProps, PropsWithChildren } from 'react';
import { cn } from '@/libs/cn';

type Props = ComponentProps<'button'> & {
  loading?: boolean;
  className?: string;
};

export default function Button({
  children,
  onClick,
  loading,
  type = 'button',
  className,
}: PropsWithChildren<Props>) {
  return (
    <button
      className={cn(
        'rounded-md bg-zinc-800 p-2 font-bold text-white',
        className,
      )}
      type={type}
      onClick={onClick}
    >
      {loading ? 'Loading...' : children}
    </button>
  );
}
