﻿using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Ticket.Api.DAL;
using Ticket.Api.Models;

namespace Ticket.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SistemasController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public SistemasController(TicketDbContext context)
        {
            _context = context;
        }

        #region INTERNOS

        /* OBTENER TODOS LOS SISTEMAS */
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Sistemas>>> GetSistemas()
        {
            return await _context.Sistemas.ToListAsync();
        }

        /* OBTENER SISTEMAS CON MAS TICKETS */
        [HttpGet("/MoreTickets")]
        public async Task<ActionResult<IEnumerable<Sistemas>>> GetSistemasConMasTickets()
        {
            return await _context.Sistemas
                .Include(s => s.Tickets)
                .OrderByDescending(s => s.Tickets.Count())
                .Take(5)
                .ToListAsync();
        }

        /* OBTENER SISTEMA POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Sistemas>> GetSistemas(int id)
        {
            var sistemas = await _context.Sistemas.Where(s => s.SistemaId == id).Include(s => s.Tickets).FirstOrDefaultAsync();

            if (sistemas == null)
            {
                return NotFound();
            }

            return sistemas;
        }

        /* UPDATE SISTEMA */
        [HttpPut("{id}")]
        public async Task<IActionResult> PutSistemas(int id, Sistemas sistemas)
        {
            if (id != sistemas.SistemaId)
            {
                return BadRequest();
            }

            _context.Entry(sistemas).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SistemasExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        /* CREATE SISTEMA */
        [HttpPost]
        public async Task<ActionResult<Sistemas>> PostSistemas(Sistemas sistemas)
        {
            _context.Sistemas.Add(sistemas);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetSistemas", new { id = sistemas.SistemaId }, sistemas);
        }

        /* DELETE SISTEMA */
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteSistemas(int id)
        {
            var sistemas = await _context.Sistemas.FindAsync(id);
            if (sistemas == null)
            {
                return NotFound();
            }

            _context.Sistemas.Remove(sistemas);
            await _context.SaveChangesAsync();

            return NoContent();
        }
        #endregion

        #region UTILS
        private bool SistemasExists(int id)
        {
            return _context.Sistemas.Any(e => e.SistemaId == id);
        }
        #endregion
    }
}
