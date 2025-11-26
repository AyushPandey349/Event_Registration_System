import { PageHeader } from '@/components/page-header';
import { bookings } from '@/lib/placeholder-data';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table';
import { Badge } from '@/components/ui/badge';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import { cn } from '@/lib/utils';
import { MoreHorizontal } from 'lucide-react';
import { Button } from '@/components/ui/button';
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuTrigger,
  } from '@/components/ui/dropdown-menu';

export default function BookingsPage() {
  return (
    <>
      <PageHeader
        title="My Bookings"
        description="View and manage your past and upcoming event bookings."
      />

      <Card>
        <CardHeader>
          <CardTitle>Booking History</CardTitle>
          <CardDescription>A list of all your event tickets.</CardDescription>
        </CardHeader>
        <CardContent>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Event</TableHead>
                <TableHead className="hidden sm:table-cell">Date</TableHead>
                <TableHead className="hidden md:table-cell">Location</TableHead>
                <TableHead className="hidden sm:table-cell">Status</TableHead>
                <TableHead className="text-right">Actions</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {bookings.map((booking) => (
                <TableRow key={booking.id}>
                  <TableCell>
                    <div className="font-medium">{booking.eventTitle}</div>
                    <div className="text-sm text-muted-foreground md:hidden">{new Date(booking.eventDate).toLocaleDateString()}</div>
                  </TableCell>
                  <TableCell className="hidden sm:table-cell">{new Date(booking.eventDate).toLocaleDateString()}</TableCell>
                  <TableCell className="hidden md:table-cell">{booking.location}</TableCell>
                  <TableCell className="hidden sm:table-cell">
                    <Badge
                      variant={
                        booking.status === 'Confirmed'
                          ? 'default'
                          : booking.status === 'Cancelled'
                          ? 'destructive'
                          : 'secondary'
                      }
                      className={cn(booking.status === 'Confirmed' && 'bg-green-600') }
                    >
                      {booking.status}
                    </Badge>
                  </TableCell>
                  <TableCell className="text-right">
                  <DropdownMenu>
                      <DropdownMenuTrigger asChild>
                        <Button aria-haspopup="true" size="icon" variant="ghost">
                          <MoreHorizontal className="h-4 w-4" />
                          <span className="sr-only">Toggle menu</span>
                        </Button>
                      </DropdownMenuTrigger>
                      <DropdownMenuContent align="end">
                        <DropdownMenuLabel>Actions</DropdownMenuLabel>
                        <DropdownMenuItem>View Ticket</DropdownMenuItem>
                        <DropdownMenuItem>Contact Organizer</DropdownMenuItem>
                        <DropdownMenuItem className="text-destructive">Cancel Booking</DropdownMenuItem>
                      </DropdownMenuContent>
                    </DropdownMenu>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </CardContent>
      </Card>
    </>
  );
}
