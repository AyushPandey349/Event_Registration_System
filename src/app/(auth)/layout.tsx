import Image from 'next/image';
import { Sparkles } from 'lucide-react';

export default function AuthLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex min-h-screen items-center justify-center bg-background p-4">
      <div className="w-full max-w-md">
        <div className="mb-8 flex justify-center">
            <div className="flex items-center gap-2 text-2xl font-bold text-primary font-headline">
                <Sparkles className="h-8 w-8" />
                <span>EventFlow</span>
            </div>
        </div>
        {children}
      </div>
    </div>
  );
}
