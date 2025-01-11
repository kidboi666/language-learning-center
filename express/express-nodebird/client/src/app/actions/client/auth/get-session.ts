'use client';

import { ActionResponse } from '@/app/actions/action-response';
import { Me } from '@/context/use-me';

export const getSession = async (): Promise<
  ActionResponse<{ isLoggedIn: boolean; user: Me }>
> => {
  const url = process.env.NEXT_PUBLIC_SERVER_URL + '/auth/session';

  try {
    const response = await fetch(url, {
      method: 'GET',
      credentials: 'include',
    });

    const data = await response.json();

    if (data.error) {
      return {
        status: 'error',
        error: data.error,
      };
    }

    return {
      status: 'success',
      data,
    };
  } catch (err) {
    if (err instanceof Error) {
      return {
        status: 'error',
        error: {
          message: err.message,
        },
      };
    }

    return {
      status: 'error',
      error: {
        message: 'An error occurred',
      },
    };
  }
};
