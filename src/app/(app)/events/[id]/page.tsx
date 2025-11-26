import { events } from '@/lib/placeholder-data';
import Image from 'next/image';
import { notFound } from 'next/navigation';
import {
  Calendar,
  Clock,
  MapPin,
  Tag,
  User,
  Ticket,
  CreditCard,
  PartyPopper,
} from 'lucide-react';
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { Separator } from '@/components/ui/separator';
import { PageHeader } from '@/components/page-header';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog';
import { Label } from '@/components/ui/label';
import { Input } from '@/components/ui/input';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import { useState } from 'react';

export default function EventDetailPage({ params }: { params: { id: string } }) {
  // For demonstration, we'll use a hardcoded event.
  // In a real app, you would fetch this based on params.id
  const event = events.find((e) => e.id === '1');

  if (!event) {
    notFound();
  }

  return (
    <div className="mx-auto max-w-4xl">
      <div className="relative h-64 w-full overflow-hidden rounded-lg md:h-96">
        <Image
          src={event.imageUrl}
          alt={event.title}
          fill
          className="object-cover"
          data-ai-hint={event.imageHint}
        />
        <div className="absolute inset-0 bg-gradient-to-t from-black/60 to-transparent" />
        <div className="absolute bottom-0 left-0 p-6">
          <Badge variant="secondary" className="mb-2">
            {event.category}
          </Badge>
          <h1 className="font-headline text-3xl font-bold text-white md:text-5xl">
            {event.title}
          </h1>
        </div>
      </div>

      <div className="mt-8 grid grid-cols-1 gap-8 md:grid-cols-3">
        <div className="md:col-span-2">
          <h2 className="mb-4 font-headline text-2xl font-semibold">About this event</h2>
          <p className="text-muted-foreground">{event.description}</p>
          <Separator className="my-8" />
          <div>
            <h3 className="mb-4 font-headline text-xl font-semibold">Details</h3>
            <div className="space-y-4">
              <div className="flex items-start gap-4">
                <Calendar className="mt-1 h-5 w-5 text-primary" />
                <div>
                  <p className="font-medium">Date and Time</p>
                  <p className="text-muted-foreground">{new Date(event.date).toDateString()} at {event.time}</p>
                </div>
              </div>
              <div className="flex items-start gap-4">
                <MapPin className="mt-1 h-5 w-5 text-primary" />
                <div>
                  <p className="font-medium">Location</p>
                  <p className="text-muted-foreground">{event.location}</p>
                </div>
              </div>
              <div className="flex items-start gap-4">
                <User className="mt-1 h-5 w-5 text-primary" />
                <div>
                  <p className="font-medium">Organizer</p>
                  <p className="text-muted-foreground">{event.organizer}</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="md:col-span-1">
          <div className="sticky top-20 rounded-lg border bg-card p-6 shadow-sm">
            <p className="mb-4 text-center text-3xl font-bold text-primary">
              ${event.price}
              <span className="text-base font-normal text-muted-foreground"> / ticket</span>
            </p>
            <BookingDialog />
          </div>
        </div>
      </div>
    </div>
  );
}

function BookingDialog() {
  const [step, setStep] = useState(1);
  const [open, setOpen] = useState(false);

  const resetAndClose = () => {
    setStep(1);
    setOpen(false);
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button className="w-full" size="lg">
          <Ticket className="mr-2 h-5 w-5" />
          Book Tickets
        </Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-[425px]">
        {step === 1 && (
          <>
            <DialogHeader>
              <DialogTitle>Book Your Spot</DialogTitle>
              <DialogDescription>Select the number of tickets you want to purchase.</DialogDescription>
            </DialogHeader>
            <div className="grid gap-4 py-4">
              <div className="grid grid-cols-4 items-center gap-4">
                <Label htmlFor="tickets" className="text-right">Tickets</Label>
                <Select defaultValue='1'>
                    <SelectTrigger className="col-span-3">
                        <SelectValue />
                    </SelectTrigger>
                    <SelectContent>
                        <SelectItem value="1">1</SelectItem>
                        <SelectItem value="2">2</SelectItem>
                        <SelectItem value="3">3</SelectItem>
                        <SelectItem value="4">4</SelectItem>
                    </SelectContent>
                </Select>
              </div>
              <p className="text-right text-lg font-bold">Total: $150.00</p>
            </div>
            <DialogFooter>
              <Button type="button" onClick={() => setStep(2)}>
                <CreditCard className="mr-2 h-4 w-4" />
                Proceed to Payment
              </Button>
            </DialogFooter>
          </>
        )}
        {step === 2 && (
          <>
            <DialogHeader>
              <DialogTitle>Payment Simulation</DialogTitle>
              <DialogDescription>Enter dummy card details to complete the booking.</DialogDescription>
            </DialogHeader>
            <div className="grid gap-4 py-4">
              <div className="space-y-2">
                <Label htmlFor="card-number">Card Number</Label>
                <Input id="card-number" placeholder="**** **** **** 1234" />
              </div>
              <div className="grid grid-cols-3 gap-4">
                <div className="space-y-2 col-span-2">
                    <Label htmlFor="expiry">Expiry</Label>
                    <Input id="expiry" placeholder="MM/YY" />
                </div>
                <div className="space-y-2">
                    <Label htmlFor="cvc">CVC</Label>
                    <Input id="cvc" placeholder="123" />
                </div>
              </div>
            </div>
            <DialogFooter>
              <Button type="button" variant="outline" onClick={() => setStep(1)}>Back</Button>
              <Button type="button" onClick={() => setStep(3)}>Pay $150.00</Button>
            </DialogFooter>
          </>
        )}
        {step === 3 && (
            <>
                <DialogHeader>
                    <div className="mx-auto flex h-12 w-12 items-center justify-center rounded-full bg-green-100">
                      <PartyPopper className="h-6 w-6 text-green-600" />
                    </div>
                    <DialogTitle className="text-center">Booking Confirmed!</DialogTitle>
                    <DialogDescription className="text-center">
                        Your tickets for Starlight Music Festival are confirmed. A confirmation email has been sent to you.
                    </DialogDescription>
                </DialogHeader>
                <DialogFooter>
                    <Button type="button" onClick={resetAndClose} className="w-full">Done</Button>
                </DialogFooter>
            </>
        )}
      </DialogContent>
    </Dialog>
  )
}
