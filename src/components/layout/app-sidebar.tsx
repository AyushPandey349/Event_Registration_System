'use client';

import {
  Sidebar,
  SidebarHeader,
  SidebarContent,
  SidebarMenu,
  SidebarMenuItem,
  SidebarMenuButton,
  SidebarFooter,
} from '@/components/ui/sidebar';
import {
  LayoutDashboard,
  Calendar,
  Ticket,
  Shield,
  Sparkles,
  User,
  LogOut,
  Settings,
} from 'lucide-react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { UserNav } from './user-nav';
import { useIsMobile } from '@/hooks/use-mobile';
import { Separator } from '../ui/separator';

const menuItems = [
  { href: '/dashboard', label: 'Dashboard', icon: LayoutDashboard },
  { href: '/events', label: 'Events', icon: Calendar },
  { href: '/bookings', label: 'My Bookings', icon: Ticket },
  { href: '/admin', label: 'Admin Panel', icon: Shield },
];

export function AppSidebar() {
  const pathname = usePathname();
  const isMobile = useIsMobile();

  const isActive = (path: string) => {
    if (path === '/dashboard') return pathname === path;
    return pathname.startsWith(path);
  };

  return (
    <Sidebar variant="inset" collapsible="icon">
      <SidebarHeader>
        <Link href="/dashboard" className="flex items-center gap-2">
          <Sparkles className="size-8 shrink-0 text-accent" />
          <span className="font-headline text-xl font-bold">EventFlow</span>
        </Link>
      </SidebarHeader>
      <SidebarContent>
        <SidebarMenu>
          {menuItems.map((item) => (
            <SidebarMenuItem key={item.href}>
              <Link href={item.href} legacyBehavior passHref>
                <SidebarMenuButton
                  isActive={isActive(item.href)}
                  tooltip={item.label}
                >
                  <item.icon />
                  <span>{item.label}</span>
                </SidebarMenuButton>
              </Link>
            </SidebarMenuItem>
          ))}
        </SidebarMenu>
      </SidebarContent>
      <SidebarFooter>
        {isMobile && <Separator className="my-2 bg-sidebar-border" />}
        {isMobile && (
           <div className="p-2">
            <UserNav />
           </div>
        )}
      </SidebarFooter>
    </Sidebar>
  );
}
