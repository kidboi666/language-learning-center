'use client';

import Button from '@/components/Button';
import { PropsWithChildren } from 'react';
import { ActionResponse } from '@/app/actions/action-response';

type Props = {
  action: () => Promise<ActionResponse<any>>;
};

export default function FetchButton({
  action,
  children,
}: PropsWithChildren<Props>) {
  return <Button onClick={action}>{children}</Button>;
}
